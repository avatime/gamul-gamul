import styled from "@emotion/styled";
import { useEffect, useState } from "react";
import { OfflineMartInfo } from "../apis/responses/offlineMartInfo";
import { ApiClient } from "../apis/apiClient";

declare global {
  interface Window {
    kakao: any;
  }
}

interface MapProps {
  mapId: string;
  ingredientId: number;
  latitude: any;
  longitude: any;
  onSetStoreId?: Function;
  onSetStoreName?: Function;
  onSetStores?: Function;
  inputHeight: string;
  Clickable?: boolean;
}

function OfflineMartMap({
  mapId,
  ingredientId = 1,
  latitude,
  longitude,
  onSetStoreId,
  onSetStoreName,
  onSetStores,
  inputHeight,
  Clickable,
}: MapProps) {
  const apiClient = ApiClient.getInstance();
  const markers: any[] = [];

  function setStoreId(storeid: number) {
    return function () {
      onSetStoreId && (onSetStoreId(storeid));
      console.log(storeid);
    };
  }

  function setStoreName(storename: string) {
    return function () {
      onSetStoreName && (onSetStoreName(storename));
      console.log(storename);
    }
  }

  function setStores(store: OfflineMartInfo[]) {
    onSetStores && (onSetStores(store));
  }

  useEffect(() => {
    const mapScript = document.createElement("script");

    mapScript.async = true;
    mapScript.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=6ea414c47933a341d2500550a054a5e2&autoload=false`;

    document.head.appendChild(mapScript);

    const onLoadKakaoMap = () => {
      window.kakao.maps.load(() => {
        const container = document.getElementById(mapId);
        const options = {
          center: new window.kakao.maps.LatLng(latitude, longitude),
          level: 4,
        };
        const map = new window.kakao.maps.Map(container, options);
        // const zoomControl = new window.kakao.maps.ZoomControl();
        // map.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT);

        //map.setZoomable(false);

        var selectedMarker: any = null;

        async function getInfo() {
          // 지도의 현재 중심좌표를 얻어옵니다
          var center = map.getCenter();

          // 지도의 현재 영역을 얻어옵니다 남서(qa, ha) 북동(pa, oa)
          var bounds = map.getBounds();

          var store = await apiClient.getOfflineMartList(
            ingredientId,
            bounds.qa,
            bounds.ha,
            bounds.pa,
            bounds.oa,
            center.getLat(),
            center.getLng()
          );

          if(store.length > 10) {
            store = store.slice(0, 10);
          }

          setStores(store);

          makeMarker(store);
          
        }

        window.kakao.maps.event.addListener(map, "dragend", getInfo);
        window.kakao.maps.event.addListener(map, "zoom_changed", getInfo);

        const imageSrc = "/non_select_temp.png",
          imageSize = new window.kakao.maps.Size(30, 30),
          imageOption = { offset: new window.kakao.maps.Point(0, 0) };

        const markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

        const imageSrc2 = "/select_temp.png",
          imageSize2 = new window.kakao.maps.Size(30, 30),
          imageOption2 = { offset: new window.kakao.maps.Point(0, 0) };

        const clickImage = new window.kakao.maps.MarkerImage(imageSrc2, imageSize2, imageOption2);

        const makeMarker = (store: OfflineMartInfo[]) => {
          markers?.forEach((v) => {
            v.setMap(null)
          })
          markers.length = 0;

          store?.forEach((v) => {
            const markerPosition = new window.kakao.maps.LatLng(v.latitude, v.longitude);
            const marker = new window.kakao.maps.Marker({
              position: markerPosition,
              image: markerImage,
            });
            marker.markerImage = markerImage;
    
            var customOverlay = new window.kakao.maps.CustomOverlay({
              map: map,
              content: `<div style="padding:0 5px;background:#fff;font-size:10px;">${v.name}</div>`,
              position: new window.kakao.maps.LatLng(v.latitude, v.longitude), // 커스텀 오버레이를 표시할 좌표
              xAnchor: 0, // 컨텐츠의 x 위치
              yAnchor: -2.75, // 컨텐츠의 y 위치
            });
            
            markers.push(marker);
            marker.setMap(map);
            customOverlay.setMap(map);

            if(!!selectedMarker && marker.getPosition().getLat() == selectedMarker.lat && marker.getPosition().getLng() == selectedMarker.lng) {
              selectedMarker = marker;
              selectedMarker.lat = marker.getPosition().getLat();
              selectedMarker.lng = marker.getPosition().getLng();
              marker.setImage(clickImage);
            }
    
            window.kakao.maps.event.addListener(marker, "click", setStoreId(v.store_id));
            window.kakao.maps.event.addListener(marker, "click", setStoreName(v.name));
    
            (!!Clickable && window.kakao.maps.event.addListener(marker, "click", function () {
              if (!selectedMarker || selectedMarker !== marker) {
                // 클릭된 마커 객체가 null이 아니면
                // 클릭된 마커의 이미지를 기본 이미지로 변경하고
                !!selectedMarker && selectedMarker.setImage(selectedMarker.markerImage);
    
                // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
                marker.setImage(clickImage);
              }
    
              // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
              selectedMarker = marker;
              selectedMarker.lat = marker.getPosition().getLat();
              selectedMarker.lng = marker.getPosition().getLng();
            }));
            
          });
        };
        
        getInfo();

      });
    };
    mapScript.addEventListener("load", onLoadKakaoMap);

    return () => mapScript.removeEventListener("load", onLoadKakaoMap);
  }, [latitude, longitude]);

  return <div id={mapId} style={{height: `${inputHeight}`}} />;
}

export default OfflineMartMap;
