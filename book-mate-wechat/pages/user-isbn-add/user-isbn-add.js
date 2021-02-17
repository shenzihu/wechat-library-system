// pages/user-isbn-add/user-isbn-add.js
Page({
  data: {
    errorStatus: 0,
    book_isbn: "",
    bookName: "",
  },
  onLoad: function (option) {
    let errorStatus = option.errorStatus;
    if (errorStatus == 1) {
      this.setData({
        errorStatus: 1
      });
    }
  },
  formSubmit: function (e) {
    let isbn = e.detail.value.book_isbn;
    let bookName = e.detail.value.bookName;
    let user = getApp().globalData.user;
    let vm = this;
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
        url: getApp().globalData.url + 'api-isbn-add-isbn',
        data: {
          isbn: isbn,
          bookName: bookName,
          userid: user.userId
        },
        method: 'GET',
        success: function (res) {
          if (res.data == 1) {
            //添加成功
            wx.showToast({
              title: '添加成功',
              icon: 'success',
              duration: 2000
            });
            vm.setData({
              book_isbn: ""
            });
            vm.setData({
              bookName: ""
            });
          } else {
            //添加失败
            wx.showToast({
              title: '添加失败',
              icon: 'cancel',
              duration: 2000
            });
            wx.redirectTo({
              url: '/pages/user-isbn-add/user-isbn-add?errorStatus=1'
            })
          }
        }
      });
    }
  }
})