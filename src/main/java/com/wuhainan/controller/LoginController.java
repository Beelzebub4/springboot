package com.wuhainan.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.wuhainan.service.SysUserService;
import com.wuhainan.utils.R;
import com.wuhainan.utils.ShiroUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录处理控制层
 * <br>Created by Admin on 2017/5/5.
 * <br>星期五 at 10:20.
 */
@Controller
public class LoginController extends AbstractController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private Producer producer;

    /**
     * 登录页面
     *
     * @return login
     */
    @RequestMapping("login")
    public R login() {
        return R.ok("login");
    }

    /**
     * 主页
     *
     * @return main
     */
    @RequestMapping("main")
    public R main() {
        return R.ok("main");
    }

    /**
     * 主页面
     *
     * @param model 前后台传值model
     * @return logon
     */
    @RequestMapping("logon")
    public R logon(Model model) {
        //当前用户
        model.addAttribute("currentUser", getSysUser().getUsername());
        //当前用户菜单列表
        model.addAttribute("menuList", sysUserService.userMenuIdList(getUserId()));
        return R.ok("logon");
    }

    /**
     * 用户菜单列表
     * @return 菜单列表
     */
    @ResponseBody
    @RequestMapping("userMenuIdList")
    public R userMenuIdList() {
        return R.ok().put("userMenuIdList", sysUserService.userMenuIdList(getUserId()));
    }

    /**
     * 验证码
     *
     * @param response response
     * @throws ServletException 异常
     * @throws IOException      异常
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 登录处理
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @return 跳转页面
     */
    @ResponseBody
    @RequestMapping(value = "doLogin")
    public R doLogin(String username, String password, String captcha) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return R.error("验证码不正确");
        }
        try {
            password = new Sha256Hash(password).toHex();
            userLogin(username, password);
        } catch (UnknownAccountException | IncorrectCredentialsException |
                LockedAccountException e) {
            return R.error(e.getMessage());
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }
        getSubject().getSession().setTimeout(14400000);//4个小时 负数永不超时 单位ms
        return R.ok("logon");//根据返回值可以设定不同页面
    }

    /**
     * 退出
     */
    @ResponseBody
    @RequestMapping(value = "doLogout", method = RequestMethod.GET)
    public R logout() {
        ShiroUtils.logout();
        return R.ok("login");
    }
}
