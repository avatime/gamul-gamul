import { NextPage } from 'next';
import React, { FC, Fragment, useEffect, useState } from 'react'
import { OfflineMartInfo } from '../src/apis/responses/offlineMartInfo';
import OfflineMartMap from '../src/components/map/OfflineMartMap';
import UseGeolocation from '../src/components/map/UseGeolocation';
import { ApiClient } from '../src/apis/apiClient';

interface IProps {
  storeInfo: OfflineMartInfo[]
}

// 37.501755, 127.0394531
const Maptest:NextPage<IProps> = ({storeInfo}) => {
    const [storeId, setStoreId] = useState(0);

    const storeIdHandler = (storeid : number) => {
      setStoreId(storeid);
    }

    const location : any = UseGeolocation();

  return (
    <Fragment>
      <div className="App">
        <h2>주변 마트</h2>
      </div>
      <OfflineMartMap latitude={location.coordinates.lat} longitude={location.coordinates.lng} arr={storeInfo} onSetStoreId={storeIdHandler} />
      <h3>마트 이름</h3>
      <h3>{storeId}</h3>
    </Fragment>
   )
 }

export default Maptest;

export async function getServerSideProps() {
  const apiClient = ApiClient.getInstance();
  // 실 구현시 useEffect로 변환 가능성
  const storeInfo : OfflineMartInfo[] = await apiClient.getOfflineMartList(1, 1, 1, 1, 1);
  return {
    props: {
      storeInfo
    }
  }
}
