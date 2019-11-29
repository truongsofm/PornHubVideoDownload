# Parse PornHub video link, get video address


### Principle
- Because PornHub uses js obfuscation, you can get the video address by directly concatenating the string after reverse js code, but not, so use Java to process the string

### Project Demo

- www.guih.club:9090



### API:

- Request interface address: www.guih.club:9090/resolveVideo
- Request method: POST
- Request parameter type: String
- Request parameter name: uri
- Is the request parameter required: True
- Return type: JSON

TIPS
- Because PornHub area in the country is not directly accessible, so if you need to run on the machine you will need this line of code ProcessUrl class uncommented, marked with their own proxy server IP and port <br>
`` `java
client.getHostConfiguration (). setProxy ("127.0.0.1", 1080);
`` `
- Here is a video address for testing (https://www.pornhub.com/view_video.php?viewkey=ph5c0491898480f), you can use this address to try it on the project demo page

### My personal blog: www.guih.club

### [中文介绍](https://github.com/Guih7/PornHubVideoDownload/blob/master/README.md)

