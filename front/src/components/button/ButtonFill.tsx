import { text } from 'node:stream/consumers';
import React, { FC } from 'react'
import styles from  '../../../styles/Button.module.css';
interface IProps {
text:string;

}

/**
* @author
* @function @
**/ 


export const ButtonFill:FC<IProps> = ({text}) => {
  return (
   
    <div>
      <button className={styles.buttonFillStyle}>{text}</button>
    </div>
   )
 }
