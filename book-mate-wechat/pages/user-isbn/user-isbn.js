// pages/user-history/user-history.js
Page({
  data: {
    isbns: {}
  },
  onLoad: function (options) {
    var that = this;
    let user = getApp().globalData.user;
    
    if (user == null) {
      wx.showModal({
        title: '提示',
        content: '您暂时没有登陆，请先登陆',
        success: function (res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '/pages/index/index',
            })
          } else {
            return
          }
        }
      })
    } else {
      var userId = user.userId;
      wx.request({
        url: getApp().globalData.url + 'api-isbn-findmy-isbn/' + userId,
        data: {},
        method: 'GET',
        success: function (res) {
          var data = res.data;
          that.setData({
            isbns: data
          });
        }
      })
    }

  }
})