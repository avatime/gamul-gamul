import React, { FC, Fragment, useEffect, useState } from 'react'
import OfflineMartMap from '../src/components/map/OfflineMartMap';
import useGeolocation from '../src/components/map/useGeolocation';

interface IProps {}

const Maptest:FC<IProps> = (props) => {
    const [lat, setLat] = useState(0);
    const [lng, setLng] = useState(0);

    //const [location, setLocation] = useState(useGeolocation());
    const location = useGeolocation();

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
      <OfflineMartMap latitude={37.5128064} longitude={127.0284288} />
    </Fragment>
   )
 }

export default Maptest;
