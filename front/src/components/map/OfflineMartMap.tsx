import styled from "@emotion/styled";
import { useEffect } from "react";

declare global {
    interface Window {
      kakao: any;
    }
  }

interface MapProps {
  latitude: any;
  longitude: any;
  arr : any;
}

function OfflineMartMap({ latitude, longitude, arr }: MapProps) {
  useEffect(() => {
    const mapScript = document.createElement("script");

    mapScript.async = true;
    mapScript.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.NEXT_PUBLIC_KAKAOMAP_APPKEY}&autoload=false`;

    document.head.appendChild(mapScript);

    const onLoadKakaoMap = () => {
      window.kakao.maps.load(() => {
        const container = document.getElementById("map");
        const options = {
          center: new window.kakao.maps.LatLng(latitude, longitude),
          level: 4,
        };
        const map = new window.kakao.maps.Map(container, options);
        // const zoomControl = new window.kakao.maps.ZoomControl();
        // map.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT);
        //map.setZoomable(false);

        const imageSrc = './select_temp.png',
        imageSize = new window.kakao.maps.Size(30, 30),
        imageOption = {offset: new window.kakao.maps.Point(0, 0)};

        const markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
        const markerPosition = new window.kakao.maps.LatLng(latitude, longitude);

        const marker = new window.kakao.maps.Marker({
          position: markerPosition,
          image: markerImage
        });
        marker.setMap(map);
        for(var i = 0; i<arr.length;i++) {
          const imageSrc = './non_select_temp.png',
          imageSize = new window.kakao.maps.Size(30, 30),
          imageOption = {offset: new window.kakao.maps.Point(0, 0)};

        const markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

          const markerPosition = new window.kakao.maps.LatLng(arr[i].latitude, arr[i].longitude);
          const marker = new window.kakao.maps.Marker({
          position: markerPosition,
          image: markerImage
        });
        var customOverlay = new window.kakao.maps.CustomOverlay({
          map: map,
          content: `<div style="padding:0 5px;background:#fff;">${arr[i].name}</div>`, 
          position: new window.kakao.maps.LatLng(arr[i].latitude, arr[i].longitude), // 커스텀 오버레이를 표시할 좌표
          xAnchor: 0, // 컨텐츠의 x 위치
          yAnchor: -1.5 // 컨텐츠의 y 위치
        });

        marker.setMap(map);
        customOverlay.setMap(map);
        
        }
      });
    };
    mapScript.addEventListener("load", onLoadKakaoMap);

    return () => mapScript.removeEventListener("load", onLoadKakaoMap);
  }, [latitude, longitude]);

  return (
    <MapContainer id="map" />
  );
}

const MapContainer = styled.div`
  width: 700px; height:350px; margin:0px 25px 0px;
`;

export default OfflineMartMap;