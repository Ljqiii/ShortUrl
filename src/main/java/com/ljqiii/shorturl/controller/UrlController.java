package com.ljqiii.shorturl.controller;

import com.ljqiii.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Controller
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/admin/deleteshorturl")
    public String deleteshorturl(@RequestParam("deleteshorturl") String deleteshorturl, Model model) {
        String[] split = deleteshorturl.split("/");
        String id = split[split.length - 1];
        int line = urlService.deleteShortUrl(id);
        if (line == 1) model.addAttribute("ok", true);
        else model.addAttribute("ok", false);
        return "deleteresult";
    }



    @PostMapping("/add")
    public String urladd(@RequestParam("originurl") String originurl,
                         @RequestParam("expiretime") Integer expiretime,
                         HttpServletRequest httpServletRequest, Model model) {
        if (!(originurl.startsWith("http://") || originurl.startsWith("https://"))) {
            originurl = "http://" + originurl;
        }

        String shorturl;

        if (expiretime == 0) {
            shorturl = urlService.createShortUrl(originurl);
        }

        Timestamp e = new Timestamp(System.currentTimeMillis() + expiretime * 24 * 60 * 60 * 1000);
        shorturl = urlService.createShortUrl(originurl, e);

        String remoteUrl = httpServletRequest.getRequestURL().toString();
        String urlrefix = remoteUrl.substring(0, remoteUrl.lastIndexOf("/") + 1);
        model.addAttribute("shorturl", urlrefix + "url/" + shorturl);

        return "result";
    }

    @GetMapping("/url/{urlid}")
    public String toOriginUrl(@PathVariable("urlid") String urlid, Model model) {
        String originurl = urlService.queryOriginUrl(urlid);
        if (originurl==null) return "404";
        if (originurl != null) model.addAttribute("originurl", originurl);
        return "redirect";
    }

}
