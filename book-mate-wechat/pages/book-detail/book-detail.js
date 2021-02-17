// pages/book-detail/book-detail.js
Page({
  data: {
    book: {},
    bookCount: {}
  },
  onLoad: function (options) {
    var that = this;
    let bookId = options.id;
    wx.request({
      url: getApp().globalData.url + "api-book-book-byid/" + bookId,
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({
          book: res.data
        });
        var obj = getApp().globalData.bookOrderList;
        var tmp = 'bookCount.' + that.data.book.bookId;
        that.setData({
          [tmp]: obj[that.data.book.bookId]
        });
      }
    })
  },
  orderBtn: function (event) {
    var bookId = event.currentTarget.id;
    var that = this;
    wx.showModal({
      title: '确认预定本书吗',
      content: '提交后请到已提交预定图书查看预定情况',
      confirmColor: '#4db6ac',
      success: function (res) {
        if (res.confirm) {
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
            wx.request({
              url: getApp().globalData.url + "api-order-addOrder/" + user.userId,
              data: JSON.stringify(that.data.bookCount),
              method: 'POST',
              header: {
                contentType: "application/json"
              },
              success: function (res) {
                var obj = getApp().globalData.bookOrderList;
                that.setData({
                  bookCount: {}
                });
                delete getApp().globalData.bookOrderList[bookId]
              }
            })
          }

        } else if (res.cancel) {
          return;
        }
      }
    })
  },
  bookDetailBtn: function (event) {
    var bookId = event.currentTarget.id;
    wx.redirectTo({
      url: '/pages/book-detail/book-detail?id=' + bookId,
    })
  },

  /* 点击减号 */
  bindMinus: function (e) {
    var id = e.currentTarget.id;
    var num = this.data.bookCount[id];
    if (num == undefined) {
      num = 0;
    }
    // 如果大于1时，才可以减  
    if (num > 0) {
      num--;
    }
    var tmp = 'bookCount.' + id;
    this.setData({
      [tmp]: num
    });
    if(num == 0){
      delete getApp().globalData.bookOrderList[id] ;
    }else{
      getApp().globalData.bookOrderList[id] = num;
    }
  },
  /* 点击加号 */
  bindPlus: function (e) {
    var id = e.currentTarget.id;
    var num = this.data.bookCount[id];
    if (num == undefined) {
      num = 0;
    }
    // 不作过多考虑自增1  
    num++;
    //每人最多两本
    if (num > 2) {
      wx.showModal({
        title: '提示',
        content: '每人每书最多两本',
        success: function (res) {
          return;
        }
      })
      return;
    }
    // 将数值与状态写回  
    var tmp = 'bookCount.' + id;
    this.setData({
      [tmp]: num
    });
    getApp().globalData.bookOrderList[id] = num;
  },
  /* 输入框事件 */
  bindManual: function (e) {
    var id = e.currentTarget.id;
    var tmp = 'bookCount.' + id;
    var num = e.detail.value;
    let that = this;
    if (num > 2) {
      wx.showModal({
        title: '提示',
        content: '每人每书最多两本',
        success: function (res) {
          return;
        }
      })
      num = 2;
    }
    if(num < 0){
      wx.showModal({
        title: '提示',
        content: '输入信息有误',
        success: function (res) {
          return;
        }
      })
      num = 0;
    }
    // 将数值与状态写回  
    this.setData({
      [tmp]: num
    });
    if(num == 0){
      delete getApp().globalData.bookOrderList[id] ;
    }else{
      getApp().globalData.bookOrderList[id] = num;
    }

  },
  addOrderlistBtn: function () {
    wx.showToast({
      title: '添加成功',
      icon: 'success',
      duration: 2000
    });
  }
})