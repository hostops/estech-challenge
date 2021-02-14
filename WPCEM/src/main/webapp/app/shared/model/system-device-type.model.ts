import { ISystemDevice } from 'app/shared/model/system-device.model';

export const enum DeviceType {
    CONTROLLER = 'CONTROLLER',
    CONFIGURABLE = 'CONFIGURABLE',
    PASSIVE = 'PASSIVE',
    SENSOR = 'SENSOR'
}

export interface ISystemDeviceType {
    id?: number;
    name?: string;
    description?: string;
    deviceType?: DeviceType;
    dataSheet?: string;
    devices?: ISystemDevice[];
}

export class SystemDeviceType implements ISystemDeviceType {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public deviceType?: DeviceType,
        public dataSheet?: string,
        public devices?: ISystemDevice[]
    ) {}
}
