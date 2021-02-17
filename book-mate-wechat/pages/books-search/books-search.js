// pages/books-search/books-search.js
Page({
  data:{
      books: {},
      bookCount: {}
    },
  onLoad: function(options) {
    var search = options.search;
    var that = this;
    wx.showNavigationBarLoading();
    wx.request({
      url: getApp().globalData.url + 'api-book-book-bynamelike/' + search,
      data: {},
      method: 'GET',
      success: function(res){
        that.setData({books: res.data});
      }
    })
    var obj = getApp().globalData.bookOrderList;
    var vm = this;
    Object.keys(obj).forEach(function (key) {
      var tmp = 'bookCount.' + key;
      vm.setData({
        [tmp]: obj[key]
      });
    });
    wx.hideNavigationBarLoading();
  },
  bookDetailBtn: function (event) {
    var bookId = event.currentTarget.id;
    wx.navigateTo({
      url: '/pages/book-detail/book-detail?id=' + bookId
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
    getApp().globalData.bookOrderList[id] = num;
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
    getApp().globalData.bookOrderList[id] = num;

  },
  addIsbnBtnOnClick: () => {
    wx.redirectTo({
      url: '/pages/user-isbn-add/user-isbn-add'
    })
  }
})