import { element, by, ElementFinder } from 'protractor';

export class SystemDeviceComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-system-device div table .btn-danger'));
    title = element.all(by.css('jhi-system-device div h2#page-heading span')).first();

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

export class SystemDeviceUpdatePage {
    pageTitle = element(by.id('jhi-system-device-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    serialNumberInput = element(by.id('field_serialNumber'));
    deviceConfigurationInput = element(by.id('field_deviceConfiguration'));
    typeSelect = element(by.id('field_type'));
    connectionsUpcomingSelect = element(by.id('field_connectionsUpcoming'));
    systemUnitSelect = element(by.id('field_systemUnit'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setSerialNumberInput(serialNumber) {
        await this.serialNumberInput.sendKeys(serialNumber);
    }

    async getSerialNumberInput() {
        return this.serialNumberInput.getAttribute('value');
    }

    async setDeviceConfigurationInput(deviceConfiguration) {
        await this.deviceConfigurationInput.sendKeys(deviceConfiguration);
    }

    async getDeviceConfigurationInput() {
        return this.deviceConfigurationInput.getAttribute('value');
    }

    async typeSelectLastOption() {
        await this.typeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async typeSelectOption(option) {
        await this.typeSelect.sendKeys(option);
    }

    getTypeSelect(): ElementFinder {
        return this.typeSelect;
    }

    async getTypeSelectedOption() {
        return this.typeSelect.element(by.css('option:checked')).getText();
    }

    async connectionsUpcomingSelectLastOption() {
        await this.connectionsUpcomingSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async connectionsUpcomingSelectOption(option) {
        await this.connectionsUpcomingSelect.sendKeys(option);
    }

    getConnectionsUpcomingSelect(): ElementFinder {
        return this.connectionsUpcomingSelect;
    }

    async getConnectionsUpcomingSelectedOption() {
        return this.connectionsUpcomingSelect.element(by.css('option:checked')).getText();
    }

    async systemUnitSelectLastOption() {
        await this.systemUnitSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async systemUnitSelectOption(option) {
        await this.systemUnitSelect.sendKeys(option);
    }

    getSystemUnitSelect(): ElementFinder {
        return this.systemUnitSelect;
    }

    async getSystemUnitSelectedOption() {
        return this.systemUnitSelect.element(by.css('option:checked')).getText();
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

export class SystemDeviceDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-systemDevice-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-systemDevice'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
