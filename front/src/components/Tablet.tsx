import React, { FC, Fragment, useEffect, useState } from "react";
import { useMediaQuery } from "react-responsive";

interface IProps {
  children: React.ReactNode;
}

export const Tablet: FC<IProps> = ({ children }) => {
  const [env, setEnv] = useState(false);
  const isTablet = useMediaQuery({ minWidth: 768, maxWidth: 991 });
  useEffect(() => {
    setEnv(isTablet);
  }, [isTablet]);
  return <Fragment>{env && children}</Fragment>;
};
