// pages/book-order/book-order.js
Page({
  data: {
    books: null,
    bookCount: {},
    right: 0,
    orderedCount: 0 //已经提交的数量
  },
  onShow: function () {
    var globalOrderList = getApp().globalData.bookOrderList;
    if (Object.keys(globalOrderList).length == 0) {
      return;
    }
    var that = this;
    wx.showNavigationBarLoading();
    wx.request({
      url: getApp().globalData.url + 'api-book-book-bybookids',
      data: JSON.stringify(Object.keys(globalOrderList)),
      method: 'POST',
      header: {
        contentType: "application/json"
      },
      success: function (res) {
        that.setData({
          books: res.data
        });
        Object.keys(globalOrderList).forEach(function (key) {
          var tmp = 'bookCount.' + key;
          that.setData({
            [tmp]: globalOrderList[key]
          });
        });
      }
    })
    let u = getApp().globalData.user;
    if (u != null) {
      //审核通过的
      wx.request({
        url: getApp().globalData.url + 'api-order-book-count/' + u.userId + '/' + 1,
        data: {},
        method: 'GET',
        success: function (res) {
          that.setData({
            orderedCount: that.data.orderedCount + res.data
          });
        }
      });
      wx.request({
        url: getApp().globalData.url + 'api-order-book-count/' + u.userId + '/' + 2,
        data: {},
        method: 'GET',
        success: function (res) {
          that.setData({
            orderedCount: that.data.orderedCount + res.data
          });
        }
      });
    }
    wx.hideNavigationBarLoading();
  },
  bookDetailBtn: function (event) {
    var bookId = event.currentTarget.id;
    wx.navigateTo({
      url: '/pages/book-detail/book-detail?scanCode=0&id=' + bookId
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
    if (num == 0) {
      delete getApp().globalData.bookOrderList[id];
    } else {
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
    if (num < 0) {
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
    if (num == 0) {
      delete getApp().globalData.bookOrderList[id];
    } else {
      getApp().globalData.bookOrderList[id] = num;
    }

  },
  /**
   * 设置movable-view位移
   */
  setXmove: function (index, xmove) {
    let books = this.data.books
    books[index].xmove = xmove

    this.setData({
      books: books
    })
  },
  /**
   * 处理touchstart事件
   */
  handleTouchStart(e) {
    this.startX = e.touches[0].pageX
  },
  /**
   * 处理touchend事件
   */
  handleTouchEnd(e) {
    if (e.changedTouches[0].pageX < this.startX && e.changedTouches[0].pageX - this.startX <= -30) {
      this.showDeleteButton(e)
    } else if (e.changedTouches[0].pageX > this.startX && e.changedTouches[0].pageX - this.startX < 30) {
      this.showDeleteButton(e)
    } else {
      this.hideDeleteButton(e)
    }
  },
  /**
   * 处理movable-view移动事件
   */
  handleMovableChange: function (e) {
    if (e.detail.source === 'friction') {
      if (e.detail.x < -30) {
        this.showDeleteButton(e)
      } else {
        this.hideDeleteButton(e)
      }
    } else if (e.detail.source === 'out-of-bounds' && e.detail.x === 0) {
      this.hideDeleteButton(e)
    }
  },

  /**
   * 删除产品
   */
  handleDeleteProduct: function ({
    currentTarget: {
      dataset: {
        id
      }
    }
  }) {
    let books = this.data.books;
    let index = books.findIndex(item => item.bookId === id);
    let bookC = this.data.bookCount;
    delete bookC[books[index].bookId + ''];
    books.splice(index, 1)
    this.setData({
      books: books,
      bookCount: bookC
    })
    delete getApp().globalData.bookOrderList[id];
  },
  /**
   * 显示删除按钮
   */
  showDeleteButton: function (e) {
    let index = e.currentTarget.dataset.index
    this.setXmove(index, -65)
  },
  /**
   * 隐藏删除按钮
   */
  hideDeleteButton: function (e) {
    let index = e.currentTarget.dataset.index
    this.setXmove(index, 0)
  },
  submitBtnOnClick: function () {
    var that = this;
    wx.showModal({
      title: '确认预定吗',
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
            let sum = that.data.orderedCount;
            for (let index in that.data.bookCount) {
              sum = sum + parseInt(that.data.bookCount[index]);
            }
            if (sum > 2) {
              debugger;
              let content = "";
              if (that.data.orderedCount >= 2) {
                content = '您已经提交了两本书，暂时无法提交新的申请';
              } else if (that.data.orderedCount > 0 && that.data.orderedCount < 2) {
                content = '您已经提交了' + that.data.orderedCount + '本书，还可以提交' + (2 - that.data.orderedCount) + '本书';
              } else {
                content = '每人最多提交两本书，请左滑删除多余的书';
              }
              wx.showModal({
                title: '提示',
                content: content,
                success: function (res) {
                  return;
                }
              })
              return;
            }
            wx.request({
              url: getApp().globalData.url + "api-order-addOrder/" + user.userId,
              data: JSON.stringify(that.data.bookCount),
              method: 'POST',
              header: {
                contentType: "application/json"
              },
              success: function (res) {
                var globalOrderList = getApp().globalData.bookOrderList;
                that.setData({
                  bookCount: {},
                  books: {}
                });
                Object.keys(globalOrderList).forEach(function (key) {
                  delete getApp().globalData.bookOrderList[key]
                });
                wx.navigateTo({
                  url: '/pages/user-order/user-order',
                })
              }
            })
          }

        } else if (res.cancel) {
          return;
        }
      }
    })
  }
})