import React, { FC, Fragment, useEffect, useState } from "react";
import { useMediaQuery } from "react-responsive";

interface IProps {
  children: React.ReactNode;
}

export const Mobile: FC<IProps> = ({ children }) => {
  const [env, setEnv] = useState(false);
  const isMobile = useMediaQuery({ maxWidth: 767 });
  useEffect(() => {
    setEnv(isMobile);
  }, [isMobile]);
  return <Fragment>{env && children}</Fragment>;
};
