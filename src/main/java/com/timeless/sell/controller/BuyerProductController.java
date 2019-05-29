package com.timeless.sell.controller;

import com.timeless.sell.entity.ProductCategory;
import com.timeless.sell.entity.ProductInfo;
import com.timeless.sell.service.CategoryService;
import com.timeless.sell.service.ProductService;
import com.timeless.sell.utils.ResultVOUtil;
import com.timeless.sell.vo.ProductInfoVO;
import com.timeless.sell.vo.ProductVO;
import com.timeless.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * @author lijiayin
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目（一次性查询）
        List<Integer> categoryTypeList = productInfoList
                .stream().map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService
                .findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        productCategoryList.forEach(productCategory -> {
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            ProductVO productVO = ProductVO.builder()
                    .categoryName(productCategory.getCategoryName())
                    .categoryType(productCategory.getCategoryType())
                    .productInfoVOList(productInfoVOList)
                    .build();
            
            productInfoList.forEach(productInfo -> {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = ProductInfoVO.builder().build();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            });
            productVOList.add(productVO);
        });
        
        return ResultVOUtil.success(productVOList);
    }
}
