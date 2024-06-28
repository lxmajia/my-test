import request from '@/utils/request'


export function getSysConfig(data) {
  return request({
    url: '/manager/getSysConfigList',
    method: 'post',
    data
  })
}


export function updateSysConfig(data) {
  return request({
    url: '/manager/updateSysConfig',
    method: 'post',
    data
  })
}
