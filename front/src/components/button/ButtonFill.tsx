import { Table } from '@mui/material';
import { text } from 'node:stream/consumers';
import React, { FC } from 'react'
import styles from  '../../../styles/Button.module.css';
import { Desktop } from '../Desktop';
import { Mobile } from '../Mobile';
import { Tablet } from '../Tablet';
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
      <Mobile>
      <button className={styles.buttonFillStyleMobile}>{text}</button>
      </Mobile>
      <Desktop>
      <button className={styles.buttonFillStyle}>{text}</button>
      </Desktop>
      <Tablet>
      <button className={styles.buttonFillStyle}>{text}</button>
      </Tablet>
    
    </div>
   )
 }
