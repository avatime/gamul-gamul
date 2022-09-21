import { Table } from '@mui/material';
import { text } from 'node:stream/consumers';
import React, { FC, MouseEventHandler } from 'react'
import styles from  '../../../styles/Button.module.css';
import { Desktop } from '../Desktop';
import { Mobile } from '../Mobile';
import { Tablet } from '../Tablet';
interface IProps {
text:string;
onClick:MouseEventHandler<HTMLInputElement>;
height:string;
width:string;

}

/**
* @author
* @function @
**/ 


export const ButtonFill:FC<IProps> = ({text,onClick, height, width}) => {
  return (
   
    <div>
      <input type="button" value={text} className={styles.buttonFillStyle} onClick={onClick} style={{width:`${width}`, height:`${height}`,cursor:"pointer"}}/> 
    </div>
   )
 }
