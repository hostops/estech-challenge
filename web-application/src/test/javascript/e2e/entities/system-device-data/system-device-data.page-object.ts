import { element, by, ElementFinder } from 'protractor';

export class SystemDeviceDataComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-system-device-data div table .btn-danger'));
    title = element.all(by.css('jhi-system-device-data div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SystemDeviceDataUpdatePage {
    pageTitle = element(by.id('jhi-system-device-data-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    timestampInput = element(by.id('field_timestamp'));
    dataValueInput = element(by.id('field_dataValue'));
    dataTypeSelect = element(by.id('field_dataType'));
    nameInput = element(by.id('field_name'));
    unitInput = element(by.id('field_unit'));
    deviceSelect = element(by.id('field_device'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTimestampInput(timestamp) {
        await this.timestampInput.sendKeys(timestamp);
    }

    async getTimestampInput() {
        return this.timestampInput.getAttribute('value');
    }

    async setDataValueInput(dataValue) {
        await this.dataValueInput.sendKeys(dataValue);
    }

    async getDataValueInput() {
        return this.dataValueInput.getAttribute('value');
    }

    async setDataTypeSelect(dataType) {
        await this.dataTypeSelect.sendKeys(dataType);
    }

    async getDataTypeSelect() {
        return this.dataTypeSelect.element(by.css('option:checked')).getText();
    }

    async dataTypeSelectLastOption() {
        await this.dataTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setUnitInput(unit) {
        await this.unitInput.sendKeys(unit);
    }

    async getUnitInput() {
        return this.unitInput.getAttribute('value');
    }

    async deviceSelectLastOption() {
        await this.deviceSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async deviceSelectOption(option) {
        await this.deviceSelect.sendKeys(option);
    }

    getDeviceSelect(): ElementFinder {
        return this.deviceSelect;
    }

    async getDeviceSelectedOption() {
        return this.deviceSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class SystemDeviceDataDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-systemDeviceData-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-systemDeviceData'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
