import 'react';

type LordIconTrigger =
  | 'hover'
  | 'click'
  | 'loop'
  | 'loop-on-hover'
  | 'morph'
  | 'morph-two-way';

type LordIconProps = {
  src?: string;
  trigger?: LordIconTrigger;
  colors?: string;
  delay?: string | number;
};

type LordIconElement = React.DetailedHTMLProps<
  React.HTMLAttributes<HTMLElement>,
  HTMLElement
> &
  LordIconProps;

declare global {
  namespace JSX {
    interface IntrinsicElements {
      'lord-icon': LordIconElement;
    }
  }
}