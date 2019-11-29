# 解析PornHub视频链接，获取视频地址

### 原理
- 因为PornHub使用的是js混淆，直接逆向js代码以后拼接字符串就可以得到视频地址，但是不会，所以使用Java来进行字符串的处理

### 项目演示

- www.guih.club:9090



### API：

- 请求接口地址 : www.guih.club:9090/resolveVideo
- 请求方法 : POST
- 请求参数类型 : String
- 请求参数名称 : uri
- 请求参数是否必须 : True
- 返回类型 : JSON


TIPS
- 因为PornHub在国区是不能直接访问的，所以如果需要在机器上运行则需要将ProcessUrl类中的这行代码取消注释，打上自己代理服务器的IP和端口<br>
```java
    client.getHostConfiguration().setProxy("127.0.0.1", 1080);
```
- 这里给大家一个测试用的视频地址（https://www.pornhub.com/view_video.php?viewkey=ph5c0491898480f),可以拿着这个地址去项目演示的页面中尝试


### 我的个人博客 
 - www.guih.club
