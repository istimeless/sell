<html>
  <#include "../common/header.ftl">
  <body>
    <div id="wrapper" class="toggled">
      <#include "../common/nav.ftl">
      <div id="page-content-wrapper">
        <div class="container-fluid">
          <div class="row clearfix">
            <div class="col-md-12 column">
              <table class="table table-striped table-hover table-condensed">
                <thead>
                <tr>
                  <th>订单编号</th>
                  <th>姓名</th>
                  <th>手机号</th>
                  <th>地址</th>
                  <th>金额</th>
                  <th>订单状态</th>
                  <th>支付状态</th>
                  <th>创建时间</th>
                  <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTOPage.content as orderDTO>
                  <tr>
                    <td>${orderDTO.orderId}</td>
                    <td>${orderDTO.buyerName}</td>
                    <td>${orderDTO.buyerPhone}</td>
                    <td>${orderDTO.buyerAddress}</td>
                    <td>${orderDTO.orderAmount}</td>
                    <td>${orderDTO.getOrderStatusEnum().message}</td>
                    <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                    <td>${orderDTO.createTime}</td>
                    <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}" type="button" class="btn btn-sm btn-info">详情</a></td>
                    <td>
                      <#if orderDTO.getOrderStatus() == 0>
                      <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-sm btn-danger">取消</a></td>
                    </#if>
                  </tr>
                </#list>
                </tbody>
              </table>
            </div>
            <div class="col-md-12 column">
              <ul class="pagination pull-right">
                <#if orderDTOPage.getPageable().getPageNumber() + 1 lte 1>
                  <li class="disabled">
                    <a href=#">上一页</a>
                  </li>
                <#else>
                  <li>
                    <a href="/sell/seller/order/list?page=${orderDTOPage.getPageable().getPageNumber() - 1}">上一页</a>
                  </li>
                </#if>

                <#list 1..orderDTOPage.getTotalPages() as index>
                  <#if orderDTOPage.getPageable().getPageNumber() + 1 == index>
                    <li class="disabled">
                      <a href="/sell/seller/order/list?page=${index - 1}">${index}</a>
                    </li>
                  <#else>
                    <li>
                      <a href="/sell/seller/order/list?page=${index - 1}">${index}</a>
                    </li>
                  </#if>
                </#list>
                <#if orderDTOPage.getPageable().getPageNumber() + 1 gte orderDTOPage.getTotalPages()>
                  <li class="disabled">
                    <a href="#">下一页</a>
                  </li>
                <#else>
                  <li>
                    <a href="/sell/seller/order/list?page=${orderDTOPage.getPageable().getPageNumber() + 1}">下一页</a>
                  </li>
                </#if>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <#--  弹窗  -->
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                  <h4 class="modal-title" id="myModalLabel">
                    提醒
                  </h4>
                </div>
                <div class="modal-body">
                  你有新的订单
                </div>
                <div class="modal-footer">
                  <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
                  <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
                </div>
              </div>

            </div>

          </div>
    <#--  播放音乐  -->
    <audio id="notice" loop="loop">
      <source src="/sell/mp3/song.mp3" type="audio/mpeg">
    </audio>
    
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
      var websocket = null;
      if("websocket" in window){
          websocket = new WebSocket("ws://istimeless.natapp1.cc/sell/webSocket");
          
          websocket.onopen = function (event) {
              console.log("建立连接");
          }
  
          websocket.onclose = function (event) {
              console.log("连接关闭");
          }
  
          websocket.onmessage = function (event) {
              console.log("收到消息：" + event.data);
              $("#myModal").modal("show");
              document.getElementById("notice").play();
          }
  
          websocket.onerror = function (event) {
              alert("websocket通信发生错误");
          }
  
          window.onbeforeunload = function (event) {
              websocket.close();
          }
      }else{
          alert("该浏览器不支持websocket！");
      }
    </script>
  </body>
</html>