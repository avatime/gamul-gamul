import React, { FC, Fragment, useEffect, useState } from 'react'
import { OfflineMartInfo } from '../src/apis/responses/offlineMartInfo';
import OfflineMartMap from '../src/components/map/OfflineMartMap';
import useGeolocation from '../src/components/map/useGeolocation';

interface IProps {}

// 37.501755, 127.0394531
const Maptest:FC<IProps> = (props) => {
    const [lat, setLat] = useState(0);
    const [lng, setLng] = useState(0);
    const [arr, setArr] = useState<OfflineMartInfo[]>([
      // {store_id : 1, name : 'test1', price: 500, latitude : 37.4553689, longitude : 126.7268268},
      // {store_id : 2, name : 'test2', price: 300, latitude : 37.4563689, longitude : 126.7244268},
      // {store_id : 3, name : 'test3', price: 600, latitude : 37.4573689, longitude : 126.7258268},
      // {store_id : 4, name : 'test4', price: 200, latitude : 37.4543689, longitude : 126.7248268}
      {store_id : 1, name : 'test1', price: 500, latitude : 37.502455, longitude : 127.0396531, distance : 100},
      {store_id : 2, name : 'test2', price: 300, latitude : 37.501355, longitude : 127.0392531, distance : 200},
      {store_id : 3, name : 'test3', price: 600, latitude : 37.501955, longitude : 127.0398531, distance : 300}, 
      {store_id : 4, name : 'test4', price: 200, latitude : 37.502255, longitude : 127.0397531, distance : 400}
    ]);
    const [storeId, setStoreId] = useState(0);

    const storeIdHandler = (storeid : number) => {
      setStoreId(storeid);
      console.log("상위 컴포넌트 : "+storeid);
    }

    //const [location, setLocation] = useState(useGeolocation());
    const location : any = useGeolocation();

    // useEffect(() => {
    //     setTimeout(() => {
    //         setLat()
    //     }, 1000);
    // }, []);



  return (
    <Fragment>
      <div className="App">
        <h2>주변 마트</h2>
      </div>
      <OfflineMartMap latitude={location.coordinates.lat} longitude={location.coordinates.lng} arr={arr} onSetStoreId={storeIdHandler} />
      <h3>마트 이름</h3>
      <h3>{storeId}</h3>
    </Fragment>
   )
 }

export default Maptest;
