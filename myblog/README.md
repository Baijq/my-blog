接口说明

1. 图片上传接口 `post`

>http://localhost:8888/blog/uploadImg

- 参数：file （只能是png/jpg图片文件）
- 返回：图片访问链接
```json
{
    "code": 0,
    "data": "/img/d807e8e506844d2abc946cafa9e1a405.png",
    "success": true,
    "message": "上传成功"
}
```