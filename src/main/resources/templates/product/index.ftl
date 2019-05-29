<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
  <#include "../common/nav.ftl">
  <div id="page-content-wrapper">
    <div class="container-fluid">
      <div class="row clearfix">
        <div class="col-md-12 column">
          <form role="form" method="post" action="/sell/seller/product/save">
            <div class="form-group">
              <label for="productName">商品名称</label>
              <input name="productName" type="text" class="form-control" id="productName" value="${(productInfo.productName)!''}"/>
            </div>
            <div class="form-group">
              <label for="productPrice">商品单价</label>
              <input name="productPrice" type="text" class="form-control" id="productPrice" value="${(productInfo.productPrice)!''}"/>
            </div>
            <div class="form-group">
              <label for="productStock">商品库存</label>
              <input name="productStock" type="number" class="form-control" id="productStock" value="${(productInfo.productStock)!''}"/>
            </div>
            <div class="form-group">
              <label for="productDescription">商品描述</label>
              <input name="productDescription" type="text" class="form-control" id="productDescription" value="${(productInfo.productDescription)!''}"/>
            </div>
            <div class="form-group">
              <label for="categoryType">商品类目</label>
              <select name="categoryType" id="categoryType" class="form-control">
                <#list categoryList as category>
                  <option value="${category.categoryType}" 
                          <#if (productInfo.categoryType) ?? && (productInfo.categoryType == category.categoryType)>
                            selected
                          </#if>>
                    ${category.categoryName}
                  </option>
                </#list>
              </select>
            </div>
            <div class="form-group">
              <label for="productIcon">商品图片</label>
              <img height="150" width="150" src="${(productInfo.productIcon)!''}">
              <input name="productIcon" type="text" class="form-control" id="productIcon" value="${(productInfo.productIcon)!''}"/>
            </div>
            <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
            <button type="submit" class="btn btn-default">保存</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>