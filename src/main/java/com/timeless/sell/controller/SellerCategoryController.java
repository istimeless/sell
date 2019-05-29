package com.timeless.sell.controller;

import com.timeless.sell.entity.ProductCategory;
import com.timeless.sell.entity.ProductInfo;
import com.timeless.sell.enums.ResultEnum;
import com.timeless.sell.form.CategoryForm;
import com.timeless.sell.form.ProductForm;
import com.timeless.sell.service.CategoryService;
import com.timeless.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lijiayin
 */
@Slf4j
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 类目列表
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(){
        List<ProductCategory> categoryList = categoryService.findAll();
        return new ModelAndView("category/list", "categoryList", categoryList);
    }

    /**
     * 类目修改/新增页面
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map){
        if (categoryId != null) {
            ProductCategory category = categoryService.findOne(categoryId);
            if (category == null){
                log.error("【卖家修改类目】类目不存在，categoryId={}", categoryId);
                map.put("url", "/sell/seller/category/list");
                map.put("msg", ResultEnum.CATEGORY_NOT_EXIST.getMessage());
                return new ModelAndView("common/error", map);
            }
            map.put("category", category);
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 保存/更新
     * @param categoryForm
     * @param bindingResult
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {

        if(bindingResult.hasErrors()){
            map.put("url", "/sell/seller/category/index");
            String error = bindingResult.getFieldErrors()
                    .stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining("，"));
            map.put("msg", error);
            return new ModelAndView("common/error", map);
        }

        try {
            ProductCategory category = new ProductCategory();
            BeanUtils.copyProperties(categoryForm, category);
            categoryService.save(category);
        }catch (Exception e){
            map.put("url", "/sell/seller/category/index");
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success");
    }
}
