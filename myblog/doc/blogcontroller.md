```java
/**
     * 保存博文，博文内容由前端md编辑器生成
     *
     * @param blogBody
     * @param blogTitle
     * @return
     */
    @ApiOperation(value = "发布博文", notes = "博文标题+博文内容+博文标签")
    @PostMapping
    public RestResponse newBlog(@RequestBody @Valid AddBlogRequest request) {
        try {
            blogService.saveBlog(request.getBlogTitle(), request.getBlogBody(), request.getTagId());
            return RestResponse.success("发布成功").setCode(StatusCode.OK);
        } catch (Exception e) {
            return RestResponse.failure("非法操作");
        }
    }

    @ApiOperation(value = "根据id查询博文", notes = "博文id")
    @GetMapping("/{blogId}/{isHistory}")
    public RestResponse findBlogById(@PathVariable Integer blogId, @PathVariable boolean isHistory) {
        if (!FormatUtil.checkObjectNull(blogId, isHistory)) {
            return RestResponse.failure("参数错误");
        }
        try {
            return RestResponse.success("查询成功")
                    .setCode(StatusCode.OK)
                    .setData(blogService.findBlogById(blogId, isHistory));
        } catch (RuntimeException e) {
            return RestResponse.failure("参数错误").setCode(StatusCode.NOTFOUND);
        } catch (Exception e) {
            return RestResponse.failure("此博客不存在").setCode(StatusCode.ERROR);
        }
    }

    /**
     * 首页分页查询
     * 查询的范围在 最近10条博客 内
     *
     * @param page      页码
     * @param showCount 显示条数
     * @return
     */
    @ApiOperation(value = "首页分页查询博文", notes = "页数+显示数量")
    @GetMapping("/home/{page}/{showCount}")
    public RestResponse homeBlog(@PathVariable Integer page, @PathVariable Integer showCount) {
        if (!FormatUtil.checkPositive(page, showCount)) {
            return RestResponse.failure("参数错误");
        }

        try {
            PageHelper.startPage(page, showCount);
            List<Blog> list = blogService.findHomeBlog(page, showCount);
            return RestResponse.success("查询成功")
                    .setCode(StatusCode.OK)
                    .setData(new PageInfo(list));
        } catch (Exception e) {
            return RestResponse.failure("服务异常").setCode(StatusCode.SERVICEERROR);
        }


    }

    /**
     * 热门博文
     * 正常状态
     *
     * @return
     */
    @ApiOperation(value = "首页热门博文", notes = "首页热门博文")
    @GetMapping("/hotBlog")
    public RestResponse hotBlog() {

        try {
            return RestResponse.success("查询成功").setCode(StatusCode.OK)
                    .setData(blogService.findHotBlog());
        } catch (Exception e) {
            return RestResponse.failure("服务异常").setCode(StatusCode.SERVICEERROR);
        }
    }

    /**
     * 博文搜索
     * 正常状态
     *
     * @param search
     * @param page
     * @param showCount
     * @return
     */
    @ApiOperation(value = "分页搜索博文", notes = "搜索内容+页码+显示条数")
    @GetMapping("/searchBlog/{page}/{showCount}")
    public RestResponse searchBlog(String search,
                             @PathVariable Integer page,
                             @PathVariable Integer showCount) {
        if (!FormatUtil.checkPositive(page, showCount)) {
            return RestResponse.failure("参数错误");
        }

        if (!FormatUtil.checkStringNull(search)) {
            return RestResponse.failure("参数错误");
        }

        PageHelper.startPage(page, showCount);
        List<Blog> blogs = blogService.searchBlog(search, page, showCount);
        return RestResponse.success("查询成功").setData(new PageInfo(blogs));
    }


    @ApiOperation(value = "按月份归档博客", notes = "按月份归档博客")
    @GetMapping("/statisticalBlogByMonth")
    public RestResponse statisticalBlogByMonth() {
        try {
            return RestResponse.success("查询成功")
                    .setCode(StatusCode.OK)
                    .setData(blogService.statisticalBlogByMonth());
        } catch (IOException e) {
            return RestResponse.failure("服务异常").setCode(StatusCode.SERVICEERROR);
        }
    }

```