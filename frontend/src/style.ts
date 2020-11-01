import { createMuiTheme } from '@material-ui/core/styles';
import { orange, purple } from '@material-ui/core/colors';

export const backgroundColor = '#242424'
export const theme = createMuiTheme({
  overrides: {
    MuiContainer: {
      maxWidthXl: {
        maxWidth: '80%',
        width: '80%',
        minWidth: '80%',
      },
      maxWidthLg: {
        minHeight: '80%',
      }
    },
    MuiTableCell: {
      head: {
        width: '10%'
      },
      body: {
        width: '10%'
      }
    }
  },
  palette: {
    type: 'dark',
    primary: {
      main: orange[400],
      dark: '#242424'
    },
    secondary: {
      main: purple[400],
    },
    //error: {
      //main: blue[500],
    //},
    //warning: {
      //main: blue[500],
    //},
    //info: {
      //main: blue[500],
    //},
    //warning: {
      //main: blue[500],
    //},
  },
});