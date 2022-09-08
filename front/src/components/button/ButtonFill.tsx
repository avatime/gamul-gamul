import React, { FC } from 'react'
import styles from  '../../../styles/Button.module.css';
interface IProps {


}

/**
* @author
* @function @
**/ 


export const ButtonFill:FC<IProps> = () => {
  return (
   
    <div>
      <button className={styles.buttonFillStyle}>확 인</button>
    </div>
   )
 }
