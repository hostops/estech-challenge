import { Moment } from 'moment';

export const enum DataType {
    VIRTUAL = 'VIRTUAL',
    REAL = 'REAL'
}

export interface ISystemDeviceData {
    id?: number;
    timestamp?: Moment;
    dataValue?: number;
    dataType?: DataType;
    name?: string;
    unit?: string;
    deviceSerialNumber?: string;
    deviceId?: number;
}

export class SystemDeviceData implements ISystemDeviceData {
    constructor(
        public id?: number,
        public timestamp?: Moment,
        public dataValue?: number,
        public dataType?: DataType,
        public name?: string,
        public unit?: string,
        public deviceSerialNumber?: string,
        public deviceId?: number
    ) {}
}
