// pages/books/books.js
Page({
  data: {
    classifyOnes: {}
  },
  onLoad: function () {
    var that = this;
   
    wx.request({
      url: getApp().globalData.url + 'api-book-classifyone-all',
      data: {},
      method: 'GET',
      success: function (res) {
        that.setData({ classifyOnes: res.data });
      }
    })
  },
  booksListBtn: function (e) {
    var name = e.currentTarget.dataset.name;
    wx.navigateTo({
      url: '/pages/books-list/books-list?classifyone=' + name
    })
  },
  changeInput: function(e) {
    var search = e.detail.value;
    if (search != "") {
      wx.navigateTo({
        url: '/pages/books-search/books-search?search=' + search,
      })
    }
  }
})