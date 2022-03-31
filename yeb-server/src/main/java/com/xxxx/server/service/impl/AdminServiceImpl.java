package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.server.AdminUtils;
import com.xxxx.server.config.security.component.JwtTokenUtil;
import com.xxxx.server.mapper.AdminRoleMapper;
import com.xxxx.server.mapper.RoleMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.pojo.AdminRole;
import com.xxxx.server.pojo.ResBean;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 通过用户id查询菜单列表
     * @return
     */


    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @Override
    public ResBean login(String username, String password, String code, HttpServletRequest request) {
        //验证码
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return ResBean.error("验证码输入错误，请重新输入");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password,userDetails.getPassword())){
            return ResBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()){
            return ResBean.error("账号被禁用，请联系管理员");
        }

        //更新Security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        System.out.println(token);
        return ResBean.success("登陆成功",tokenMap);
    }
    /**
     * 根基用户id查询角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName (String username){
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username)
                .eq("enabled", true));
    }
    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(),keywords);
    }

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional
    public ResBean updateAdminRole(Integer adminId, Integer[] rids) {

        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer res = adminRoleMapper.updateAdminRole(adminId, rids);
        if (rids.length == res) {
            return ResBean.success("更新成功!");
        }
        return ResBean.error("更新失败!");
    }
}
