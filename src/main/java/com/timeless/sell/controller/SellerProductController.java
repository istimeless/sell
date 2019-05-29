package com.timeless.sell.controller;

import com.timeless.sell.dto.OrderDTO;
import com.timeless.sell.entity.ProductCategory;
import com.timeless.sell.entity.ProductInfo;
import com.timeless.sell.enums.ProductStatusEnum;
import com.timeless.sell.enums.ResultEnum;
import com.timeless.sell.exception.SellException;
import com.timeless.sell.form.ProductForm;
import com.timeless.sell.service.CategoryService;
import com.timeless.sell.service.ProductService;
import com.timeless.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
@RequestMapping("/seller/product")
public class SellerProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    /**
     * 商品列表
     * @param pageable
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@PageableDefault Pageable pageable){
        Page<ProductInfo> productInfoPage = productService.findAll(pageable);
        return new ModelAndView("product/list", "productInfoPage", productInfoPage);
    }

    /**
     * 商品修改/新增页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map){
        if (StringUtils.isNotBlank(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            if (productInfo == null){
                log.error("【卖家修改商品】商品不存在，productId={}", productId);
                map.put("url", "/sell/seller/product/list");
                map.put("msg", ResultEnum.PRODUCT_NOT_EXIST.getMessage());
                return new ModelAndView("common/error", map);
            }
            map.put("productInfo", productInfo);
        }
        
        //查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    /**
     * 上架商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map){
        map.put("url", "/sell/seller/product/list");

        try {
            productService.onSale(productId);
        }catch (SellException e){
            log.error("【卖家上架商品】发生异常：{}", e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success");
    }

    /**
     * 下架商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map){
        map.put("url", "/sell/seller/product/list");

        try {
            productService.offSale(productId);
        }catch (SellException e){
            log.error("【卖家下架商品】发生异常：{}", e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success");
    }

    /**
     * 保存/更新
     * @param productForm
     * @param bindingResult
     */
    @PostMapping("/save")
//    @CachePut(cacheNames = "product", key = "123")
    @CacheEvict(cacheNames = "product", key = "123")
    public ModelAndView save(@Valid ProductForm productForm,
                     BindingResult bindingResult,
                     Map<String, Object> map) {
        
        if(bindingResult.hasErrors()){
            map.put("url", "/sell/seller/product/index");
            String error = bindingResult.getFieldErrors()
                    .stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining("，"));
            map.put("msg", error);
            return new ModelAndView("common/error", map);
        }
        
        try {
            ProductInfo productInfo = new ProductInfo();
            if(StringUtils.isNotBlank(productForm.getProductId())){
                productInfo = productService.findOne(productForm.getProductId());
            }else{
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        }catch (Exception e){
            map.put("url", "/sell/seller/product/index");
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success");
    }
}
