import request from '@/utils/request'

export function getAppModuleStructList() {
  return request({
    url: '/manager/appModuleStructList',
    method: 'get'
  })
}


export function getSysConfig(data) {
  return request({
    url: '/manager/getSysConfigList',
    method: 'post',
    data
  })
}
