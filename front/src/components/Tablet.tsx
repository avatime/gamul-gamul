import styled from "@emotion/styled";
import React, { FC, Fragment, useEffect, useState } from "react";
import { useMediaQuery } from "react-responsive";

// interface IProps {
//   children: React.ReactNode;
// }

// export const Tablet: FC<IProps> = ({ children }) => {
//   const [env, setEnv] = useState(false);
//   const isTablet = useMediaQuery({ minWidth: 768, maxWidth: 991 });
//   useEffect(() => {
//     setEnv(isTablet);
//   }, [isTablet]);
//   return <Fragment>{env && children}</Fragment>;
// };

export const Tablet = styled.div`
  @media (min-width: 768px) and (max-width: 991px) {
    display: block;
  }
  @media (max-width: 767px) {
    display: none;
  }
  @media (min-width: 992px) {
    display: none;
  }
`;
