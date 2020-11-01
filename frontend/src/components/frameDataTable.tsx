import React from 'react';
import { createStyles, lighten, makeStyles, Theme } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import Toolbar from '@material-ui/core/Toolbar';
import Paper from '@material-ui/core/Paper';
import { frameDataModel } from '../models/models';
import { CharacterSelectDropdown} from './characterSelectDropdown';

function descendingComparator<T>(a: T, b: T, orderBy: keyof T) {
    if (b[orderBy] < a[orderBy]) {
        return -1;
    }
    if (b[orderBy] > a[orderBy]) {
        return 1;
    }
    return 0;
}

type Order = 'asc' | 'desc';

function getComparator<Key extends keyof any>( order: Order, orderBy: Key,):
    (a: { [key in Key]: number | string }, b: { [key in Key]: number | string }) => number {
    return order === 'desc'
        ? (a, b) => descendingComparator(a, b, orderBy)
        : (a, b) => -descendingComparator(a, b, orderBy);
}

function stableSort<T>(array: T[], comparator: (a: T, b: T) => number) {
    const stabilizedThis = array.map((el, index) => [el, index] as [T, number]);
    stabilizedThis.sort((a, b) => {
        const order = comparator(a[0], b[0]);
        if (order !== 0) return order;
        return a[1] - b[1];
    });
    return stabilizedThis.map((el) => el[0]);
}

interface HeadCell {
    disablePadding: boolean;
    id: keyof frameDataModel;
    label: string;
    numeric: boolean;
}

const headCells: HeadCell[] = [
    { id: 'Command', numeric: true, disablePadding: false, label: 'Command' },
    { id: 'HitLevel', numeric: true, disablePadding: false, label: 'Hit Level' },
    { id: 'Damage', numeric: true, disablePadding: false, label: 'Damage' },
    { id: 'StartUpFrames', numeric: true, disablePadding: false, label: 'Start Up Frames'},
    { id: 'BlockFrames', numeric: true, disablePadding: false, label: 'On Block'},
    { id: 'HitFrames', numeric: true, disablePadding: false, label: 'On Hit'},
    { id: 'CounterHitFrames', numeric: true, disablePadding: false, label: 'On Counter Hit'},
];

interface EnhancedTableProps {
    classes: ReturnType<typeof useStyles>;
    onRequestSort: (event: React.MouseEvent<unknown>, property: keyof frameDataModel) => void;
    order: Order;
    orderBy: string;
}

function EnhancedTableHead(props: EnhancedTableProps) {
    const { classes, order, orderBy, onRequestSort } = props;
    const createSortHandler = (property: keyof frameDataModel) => (event: React.MouseEvent<unknown>) => {
        onRequestSort(event, property);
    };

    return (
        <TableHead>
        <TableRow>
            {headCells.map((headCell) => (
            <TableCell
                key={headCell.id}
                align={'left'}
                padding={headCell.disablePadding ? 'none' : 'default'}
                sortDirection={orderBy === headCell.id ? order : false}
            >
                <TableSortLabel
                    active={orderBy === headCell.id}
                    direction={orderBy === headCell.id ? order : 'asc'}
                    onClick={createSortHandler(headCell.id)}
                >
                {headCell.label}
                {orderBy === headCell.id ? (
                    <span className={classes.visuallyHidden}>
                    {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                    </span>
                ) : null}
                </TableSortLabel>
            </TableCell>
            ))}
        </TableRow>
        </TableHead>
    );
}

const useToolbarStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            paddingLeft: theme.spacing(2),
            paddingRight: theme.spacing(2),
        },
        highlight:
            theme.palette.type === 'light'
                ? {
                    color: theme.palette.secondary.main,
                    backgroundColor: lighten(theme.palette.secondary.light, 0.85),
                }
                : {
                    color: theme.palette.text.primary,
                    backgroundColor: theme.palette.secondary.dark,
                },
        container: {
            display: 'flex'
        },
        spacer: {
            flexGrow: 1,
        },
    }),
);

interface EnhancedTableToolbarProps {
    character: string,
    characters: string[],
    changeCharacter: (character: string) => void,
    defaultSort: () => void,
}

const EnhancedTableToolbar = (props: EnhancedTableToolbarProps) => {
    const classes = useToolbarStyles();
    return (
        <Toolbar className={classes.container}>
            <CharacterSelectDropdown
                character={props.character}
                characters={props.characters}
                changeCharacter={props.changeCharacter}
            />
            <div className={classes.spacer} />
            <Button variant="outlined" onClick={props.defaultSort} color="primary">Default Sort</Button>
        </Toolbar>
    );
};

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            width: '100%',
        },
        paper: {
            width: '100%',
            paddingTop: theme.spacing(2),
            marginBottom: theme.spacing(2),
            marginTop: theme.spacing(2),
        },
        table: {
            maxWidth: '90%',
            margin: 'auto'
        },
        visuallyHidden: {
            border: 0,
            clip: 'rect(0 0 0 0)',
            height: 1,
            margin: -1,
            overflow: 'hidden',
            padding: 0,
            position: 'absolute',
            top: 20,
            width: 1,
        },
    }),
);

const initFrames = (frameData: frameDataModel[], character: string) => {
    const rows = frameData.filter(row => row.Character === character);
    return rows.sort((a, b) => (a.Id > b.Id) ? 1 : -1)
}

export const FrameDataTable = (frameData: frameDataModel[]) => {
    const [character, setCharacter] = React.useState('Asuka')
    const [order, setOrder] = React.useState<Order>('asc');
    const [orderBy, setOrderBy] = React.useState<keyof frameDataModel>('Id');
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(200);
    const classes = useStyles();
    const rows = initFrames(frameData, character);
    const characters: string[] = [];
    frameData.map(obj => {
        if (characters.indexOf(obj.Character) === -1) {
            characters.push(obj.Character)
        }
        return null
    })

    const handleRequestSort = (event: React.MouseEvent<unknown>, property: keyof frameDataModel) => {
        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const handleDefaultSort = () => {
        setOrder('asc');
        setOrderBy('Id');
    };

    const handleChangePage = (event: unknown, newPage: number) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleChangeCharacter = (character: string) => {
        setCharacter(character);
    };


    const emptyRows = rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);

    return (
        <div className={classes.root}>
        <Paper className={classes.paper}>
            <EnhancedTableToolbar
                character={character}
                characters={characters}
                changeCharacter={handleChangeCharacter}
                defaultSort={handleDefaultSort}
            />
            <TableContainer>
            <Table
                className={classes.table}
                aria-labelledby="tableTitle"
                size={'medium'}
                aria-label="enhanced table"
            >
                <EnhancedTableHead
                    classes={classes}
                    order={order}
                    orderBy={orderBy}
                    onRequestSort={handleRequestSort}
                />
                <TableBody>
                {stableSort(rows, getComparator(order, orderBy))
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((row, index) => {
                    return (
                        <TableRow
                            hover
                            tabIndex={-1}
                            key={row.Id}
                        >
                            <TableCell align="left">{row.Command}</TableCell>
                            <TableCell align="left">{row.HitLevel}</TableCell>
                            <TableCell align="left">{row.Damage}</TableCell>
                            <TableCell align="left">{row.StartUpFrames}</TableCell>
                            <TableCell align="left">{row.BlockFrames}</TableCell>
                            <TableCell align="left">{row.HitFrames}</TableCell>
                            <TableCell align="left">{row.CounterHitFrames}</TableCell>
                        </TableRow>
                    );
                    })}
                {emptyRows > 0 && (
                    <TableRow style={{ height: 53 * emptyRows }}>
                    <TableCell colSpan={6} />
                    </TableRow>
                )}
                </TableBody>
            </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[5, 10, 25]}
                component="div"
                count={rows.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onChangePage={handleChangePage}
                onChangeRowsPerPage={handleChangeRowsPerPage}
            />
        </Paper>
        </div>
    );
}