import React, { FC, Fragment, useEffect, useState } from 'react'
import OfflineMartMap from '../src/components/map/OfflineMartMap';
import useGeolocation from '../src/components/map/useGeolocation';

interface IProps {}

const Maptest:FC<IProps> = (props) => {
    const [lat, setLat] = useState(0);
    const [lng, setLng] = useState(0);
    const [arr, setArr] = useState([
      {store_id : 1, name : 'test1', price: 500, latitude : 37.4553689, longitude : 126.7268268},
      {store_id : 2, name : 'test2', price: 300, latitude : 37.4563689, longitude : 126.7244268},
      {store_id : 3, name : 'test3', price: 600, latitude : 37.4573689, longitude : 126.7258268},
      {store_id : 4, name : 'test4', price: 200, latitude : 37.4543689, longitude : 126.7248268}
    ]);

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
        {location.loaded ? JSON.stringify(location) : "Location data not available yet."}
      </div>
      <OfflineMartMap latitude={location.coordinates.lat} longitude={location.coordinates.lng} arr={arr} />
    </Fragment>
   )
 }

export default Maptest;
