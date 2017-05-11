<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Uploader上传控件</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="/js/jquery-1.8.3.min.js"></script>
    
    <!-- include -->
    <script type="text/javascript" src="/js/webuploader.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/webuploader.css">
    <!-- include -->
</head>
<body>
    <div id="uploader">
        <div class="queueList">
            <div id="dndArea" class="placeholder">
                <div id="filePicker"></div>
                <p>或将照片拖到这里，单次最多可选3011111111111111111110张</p>
            </div>
        </div>
        <div class="statusBar" style="display:none;">
            <div class="progress">
                <span class="text">0%</span>
                <span class="percentage"></span>
            </div>
            <div class="info"></div>
            <div class="btns">
                <div id="filePicker2"></div>
                <div class="uploadBtn">开始上传</div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/js/upload.js"></script>
    <script>
        // 实例化
        var uploader = WebUploader.create({
            pick: {
                id: '#filePicker',
                label: '点击选择图片'
            },
            formData: {
                uid: 123
            },
            dnd: '#dndArea',//拉拽区域div的id
            paste: '#uploader',//黏贴区域
            swf: '/js/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });
        /** 附件函数
        uploader.on( 'uploadSuccess', function( type ) {
           alert(11000);
        });
        */
    </script>
</body>
</html>