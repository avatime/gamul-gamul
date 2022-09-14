import styled from "@emotion/styled";
import React, { FC, Fragment, useEffect, useState } from "react";
import { useMediaQuery } from "react-responsive";

// interface IProps {
//   children: React.ReactNode;
// }

// export const Mobile: FC<IProps> = ({ children }) => {
//   const [env, setEnv] = useState(false);
//   const isMobile = useMediaQuery({ maxWidth: 767 });
//   useEffect(() => {
//     setEnv(isMobile);
//   }, [isMobile]);
//   return <Fragment>{env && children}</Fragment>;
// };

export const Mobile = styled.div`
  @media (max-width: 767px) {
    display: block;
  }
  @media (min-width: 768px) {
    display: none;
  }
`;
