package org.linlinjava.litemall.wx.web;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.wx.service.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/wx/test")
public class TestController {
    @Autowired
    private WxMaService wxMaService;

    @PostMapping("login")
    public Object login(String code,String avatarUrl,String gender){
        try {
            WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(code);
            String openid = result.getOpenid();
            int id = 160;
            String token = UserTokenManager.generateToken(id);
            HashMap<String, String> map = new HashMap<>();
            map.put("token",token);
            return ResponseUtil.ok(map);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

}
