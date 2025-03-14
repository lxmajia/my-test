import request from '@/utils/request'

export function getAppModuleList(data) {
  return request({
    url: '/manager/appModuleList',
    method: 'post',
    data
  })
}


export function getAppModuleStructList() {
  return request({
    url: '/manager/appModuleStructList',
    method: 'get'
  })
}

export function getConfigInfoTypeList() {
  return request({
    url: '/manager/getConfigInfoTypeList',
    method: 'get'
  })
}
