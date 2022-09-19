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
  stores : any;
  onSetStoreId : Function;
}

function OfflineMartMap({ latitude, longitude, stores, onSetStoreId }: MapProps) {
  useEffect(() => {
    const mapScript = document.createElement("script");

    mapScript.async = true;
    mapScript.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=6ea414c47933a341d2500550a054a5e2&autoload=false`;

    document.head.appendChild(mapScript);

    function makeTest(storeid: number) {
      return function() {
        onSetStoreId(storeid);
        console.log(storeid);
      };
  }

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
                
        const markerPosition = new window.kakao.maps.LatLng(latitude, longitude);

        const imageSrc = './non_select_temp.png',
        imageSize = new window.kakao.maps.Size(30, 30),
        imageOption = {offset: new window.kakao.maps.Point(0, 0)};

        const markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

        const imageSrc2 = './select_temp.png',
        imageSize2 = new window.kakao.maps.Size(30, 30),
        imageOption2 = {offset: new window.kakao.maps.Point(0, 0)};

        const clickImage = new window.kakao.maps.MarkerImage(imageSrc2, imageSize2, imageOption2);

        var selectedMarker : any = null;

        for(var i = 0; i<stores.length;i++) {
          const markerPosition = new window.kakao.maps.LatLng(stores[i].latitude, stores[i].longitude);
          const marker = new window.kakao.maps.Marker({
          position: markerPosition,
          image: markerImage,
        });
        marker.markerImage = markerImage;

        var customOverlay = new window.kakao.maps.CustomOverlay({
          map: map,
          content: `<div style="padding:0 5px;background:#fff;font-size:10px;">${stores[i].name}</div>`, 
          position: new window.kakao.maps.LatLng(stores[i].latitude, stores[i].longitude), // 커스텀 오버레이를 표시할 좌표
          xAnchor: 0, // 컨텐츠의 x 위치
          yAnchor: -2.35 // 컨텐츠의 y 위치
        });

        marker.setMap(map);
        customOverlay.setMap(map);

        window.kakao.maps.event.addListener(marker, 'click', makeTest(stores[i].store_id));
        window.kakao.maps.event.addListener(marker, 'click', function() {
          if (!selectedMarker || selectedMarker !== marker) {

            // 클릭된 마커 객체가 null이 아니면
            // 클릭된 마커의 이미지를 기본 이미지로 변경하고
            !!selectedMarker && selectedMarker.setImage(selectedMarker.markerImage);

            // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
            marker.setImage(clickImage);
        }

        // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
        selectedMarker = marker;
        });
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
width: 370px; height:300px;
`;

export default OfflineMartMap;