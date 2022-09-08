import React, { FC, Fragment, useEffect, useState } from "react";
import { useMediaQuery } from "react-responsive";

interface IProps {
  children: React.ReactNode;
}

export const Desktop: FC<IProps> = ({ children }) => {
  const [env, setEnv] = useState(false);
  const isDesktop = useMediaQuery({ minWidth: 992 });
  useEffect(() => {
    setEnv(isDesktop);
  }, [isDesktop]);
  return <Fragment>{env && children}</Fragment>;
};
