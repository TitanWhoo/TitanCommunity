package cn.titan6.community.controller;

import cn.titan6.community.bean.*;
import cn.titan6.community.service.ArticleService;
import cn.titan6.community.service.CommentService;
import cn.titan6.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RequestMapping("/api")
@SessionAttributes({"user"})
@Controller
public class ApiController {

    private final UserService userService;
    private final ArticleService articleService;
    private final CommentService commentService;

    public ApiController(UserService userService, ArticleService articleService, CommentService commentService) {
        this.userService = userService;
        this.articleService = articleService;
        this.commentService = commentService;
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse login(String account, String pass, Model model, HttpSession session) {
        User user = userService.find(account, pass);
        JsonResponse response = new JsonResponse();
        if (user != null) {
            model.addAttribute("user", user);
            response.setStatus(Status.SUCCESS);
            response.setText("登陆成功");
        } else {
            response.setStatus(Status.FAILED);
            response.setText("用户名或密码错误");
        }
        return response;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse register(@Valid User user, BindingResult bindingResult, Model model, HttpSession session) {
        JsonResponse response = new JsonResponse();
        User found = userService.find(user);
        if (found != null) {
            response.setStatus(Status.FAILED);
            response.setText("用户已存在");
            return response;
        }

        if (bindingResult.hasErrors()) {
            setResponseError(bindingResult, response);
            return response;
        }

        boolean saved = userService.save(user);
        if (saved) {
            response.setStatus(Status.SUCCESS);
            response.setText("注册成功");
            // 获取User的ID 并且更新一下User对象
            user = userService.find(user.getUsername(), user.getPassword());
            model.addAttribute("user", user);
        } else {
            response.setStatus(Status.FAILED);
            response.setText("注册失败");
        }
        return response;
    }

    @RequestMapping(path = "/post/article", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse postArticle(@Valid Article article, BindingResult result, ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        JsonResponse jsonResponse = new JsonResponse();
        if (user == null) {
            jsonResponse.setStatus(Status.FAILED);
            jsonResponse.setText("用户未登录");
            return jsonResponse;
        } else if (result.hasErrors()) {
            setResponseError(result, jsonResponse);
            return jsonResponse;
        }
        article.setAuthor(user);
        boolean postResult = articleService.post(article);
        if (!postResult) {
            jsonResponse.setStatus(Status.FAILED);
            jsonResponse.setText("发布文章时出现了错误，保存失败");
        } else {
            jsonResponse.setStatus(Status.SUCCESS);
            jsonResponse.setText("发布文章成功");
        }
        return jsonResponse;
    }

    @RequestMapping("/post/comment")
    @ResponseBody
    public JsonResponse postComment(@Valid Comment comment, BindingResult result, ModelMap modelMap) {
        JsonResponse jsonResponse = new JsonResponse();
        User user = (User) modelMap.get("user");

        if (user == null) {
            jsonResponse.setStatus(Status.FAILED);
            jsonResponse.setText("用户未登录");
            return jsonResponse;
        } else if (result.hasErrors()) {
            setResponseError(result, jsonResponse);
            return jsonResponse;
        }
        int aid = comment.getAid();
        Article article = articleService.findById(aid);
        if (article == null) {
            jsonResponse.setStatus(Status.FAILED);
            jsonResponse.setText("文章ID不存在");
            return jsonResponse;
        }
        comment.setUser(user);
        boolean postResult = commentService.post(comment);
        if (!postResult) {
            jsonResponse.setStatus(Status.FAILED);
            jsonResponse.setText("发表回复时出现了错误，保存失败");
        } else {
            jsonResponse.setStatus(Status.SUCCESS);
            jsonResponse.setText("发表回复成功");
        }
        return jsonResponse;
    }

    private void setResponseError(BindingResult result, JsonResponse jsonResponse) {
        jsonResponse.setStatus(Status.WARNING);
        StringBuilder builder = new StringBuilder();
        List<ObjectError> errors = result.getAllErrors();
        for (ObjectError error : errors) {
            String defaultMessage = error.getDefaultMessage();
            builder.append(defaultMessage).append(" ");
        }
        jsonResponse.setText(builder.toString());
    }

    @RequestMapping("/logout")
    @ResponseBody
    public JsonResponse logout(HttpSession session, SessionStatus sessionStatus) {
        session.invalidate();
        sessionStatus.setComplete();
        JsonResponse response = new JsonResponse();
        response.setStatus(Status.SUCCESS);
        response.setText("OK");
        return response;
    }
}
