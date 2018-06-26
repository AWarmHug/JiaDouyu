1. 直播/最上方推荐栏：

   https://apiv2.douyucdn.cn/live/Cate/getTabCate1List?client_sys=android

2. 推荐分类

   https://apiv2.douyucdn.cn/live/cate/getLiveRecommendCate2?client_sys=android

3. 活动

   https://apiv2.douyucdn.cn/Live/Subactivity/getActivityList?cid2=0&client_sys=android

   https://apiv2.douyucdn.cn/Live/Subactivity/getActivityList?cid2=0&client_sys=android

4. 首页分类

   https://apiv2.douyucdn.cn/live/cate/getLiveCate1List?client_sys=android

   根据cate1_id 获取分类下的

   https://apiv2.douyucdn.cn/live/cate/getLiveCate2ByCate1?cate1_id=1&limit=12&offset=12&client_sys=android

5. 首页全部

   https://apiv2.douyucdn.cn/gv2api/rkc/roomlist/0_0/0/20/android?client_sys=android

   https://apiv2.douyucdn.cn/gv2api/rkc/roomlist/0_0/20/20/android?client_sys=android

   https://apiv2.douyucdn.cn/gv2api/rkc/roomlist/0_0/40/20/android?client_sys=android

   首页中如果有需要额外图片 比如绝地求生存活人数

   https://apiv2.douyucdn.cn/ggapi/rnc/mgeticon1?rids=78561,4551310,3857053,748396,56040,276685,699689,687423,1144379,540357,2205764&flags=1,2&client_sys=android

6. 刺激战场

   https://apiv2.douyucdn.cn/Live/Customcate2/getAllComponentList?cate2_id=350&client_sys=android

   这应该是获取滚动活动列表

   https://apiv2.douyucdn.cn/live/audiolive/getAudioCid?client_sys=android

   https://apiv2.douyucdn.cn/Live/Subactivity/getActivityList?cid2=350&client_sys=android

   暂时不知道

   https://apiv2.douyucdn.cn/mgame/cate2promotion/getPromo?cate2_id=350&client_sys=android

   获推广地址    *先请求是否有推广，如果没有推广就请求轮播图

   https://apiv2.douyucdn.cn/mgame/cate2promotion/getPromo?cate2_id=181&client_sys=android

   轮播广告图

   https://apiv2.douyucdn.cn/live/Slide/getSlideLists?cate_id=350&app_ver=10430002&client_sys=android

   获取第三层级category

   https://capi.douyucdn.cn/api/v1/getThreeCate?tag_id=350&client_sys=android

   和首页全部相同 2_350指的是level_cate2

   https://apiv2.douyucdn.cn/gv2api/rkc/roomlist/2_350/0/20/android?client_sys=android

7. 获取王者荣耀的某个值

   https://apiv2.douyucdn.cn/Live/Wzry/getConfig?client_sys=android

   https://apiv2.douyucdn.cn/Live/Wzry/getAllTag2List?cate2_id=270&client_sys=android

8. 直播榜单重置时间

   https://apiv2.douyucdn.cn/live/rank/deadlineConfig?&client_sys=android

9. 获取cate1_2

   https://mconf.douyucdn.cn/resource/common/mobile/homecustomcate.json

10. 获取进入直播间等级显示信息

  https://apiv2.douyucdn.cn/Live/Noble/getNobleAllWelcomeEffectList?client_sys=android

11. 获取今日搜索热点

    https://apiv2.douyucdn.cn/video/search/getTodayTopData?limit=10&client_sys=android

12. 体育直播

    https://capi.douyucdn.cn/api/v1/qie?offset=0&limit=20&aid=android1&time=1529034473&client_sys=android

13. 获取TabCate2

    https://apiv2.douyucdn.cn/live/Cate/getTabCate2List?tab_id=3&client_sys=android

    

14. 获取动态

    https://mapi-yuba.douyu.com/wb/v3/catefeed/270?page=1

    该域名下都需要client

    dy-device-id: c49d9187c0c739fedd9e63a830505111
    phone_model: MI 5
    client: android *必备
    version: 340
    phone_system: 7.0
    timestamp: 1529054573
    Accept: application/vnd.mapi-yuba.douyu.com.4.0+json
    auth: 1d29e54ebe676393eace593743eef2b9
    Token: 
    Content-Type: application/x-www-form-urlencoded; charset=utf-8
    Host: mapi-yuba.douyu.com
    Connection: Keep-Alive
    Accept-Encoding: gzip
    User-Agent: okhttp/3.9.1

15. 获取动态详情

    https://mapi-yuba.douyu.com/post/replies/624926731528712194?page=1&floor=0&pagesize=20

16. video 分类

    https://apiv2.douyucdn.cn/Video/cate/getVideoAllCate?client_sys=android

17. 获取今日热点关键词

    https://apiv2.douyucdn.cn/live/search/getTodayHot?token=&client_sys=android

18. 鱼吧热搜

    https://mapi-yuba.douyu.com/wb/main/hotsearch2?client_sys=android

19. 猜你喜欢

    https://apiv2.douyucdn.cn/live/search/getRecFavor?token=&client_sys=android

20. 根据id 获取直播间信息

    https://apiv2.douyucdn.cn/live/room/info/4825737?client_sys=android

21. 搜索结果

    全部：参数1/1   1指全部 

    https://capi.douyucdn.cn/api/v1/mobileSearch/1/1?sk=skt&offset=0&limit=20&client_sys=android

    直播：参数2/1 2指直播 1-3指下方的相关度，热度值，关注度 

    https://capi.douyucdn.cn/api/v1/mobileSearch/2/1?sk=%E6%9D%8E%E7%9F%A5%E6%81%A9&offset=0&limit=20&client_sys=android

    视频（sort 对应相关度， 最热等）：

    https://apiv2.douyucdn.cn/video/search/getData?sk=%E6%9D%8E%E7%9F%A5%E6%81%A9&sort=1&offset=0&limit=20&client_sys=android

    主播：

    https://capi.douyucdn.cn/api/v1/mobileSearch/4/1?sk=%E6%9D%8E%E7%9F%A5%E6%81%A9&offset=0&limit=20&client_sys=android

22. 视频详情

    https://apiv2.douyucdn.cn/video/videoinfo/getvidinfo2?vid=jXqeO74mPxPMxywG&client_sys=android

23. 获取视频链接

    https://vmobile.douyu.com/video/getInfo?vid=wkDe0W2AeX2MA4Bz

24. 视频封面

    https://apiv2.douyucdn.cn/video/videoinfo/getExtVideoInfo?vid=y2Bj8vGLmbr7Obnd&client_sys=android

25. 视频中的弹幕

    https://apiv2.douyucdn.cn/video/videoinfo/getDanMu?vid=jXqeO74mPxPMxywG&client_sys=android

26. 获取推荐视频

    https://apiv2.douyucdn.cn/video/home/getRecVideoList?offset=0&limit=20&token=&client_sys=android













Cookie:acf_did=c49d9187c0c739fedd9e63a830505111

