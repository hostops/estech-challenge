import { element, by, ElementFinder } from 'protractor';

export class SystemDeviceTypeComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-system-device-type div table .btn-danger'));
    title = element.all(by.css('jhi-system-device-type div h2#page-heading span')).first();

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

export class SystemDeviceTypeUpdatePage {
    pageTitle = element(by.id('jhi-system-device-type-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    descriptionInput = element(by.id('field_description'));
    deviceTypeSelect = element(by.id('field_deviceType'));
    dataSheetInput = element(by.id('field_dataSheet'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setDescriptionInput(description) {
        await this.descriptionInput.sendKeys(description);
    }

    async getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    async setDeviceTypeSelect(deviceType) {
        await this.deviceTypeSelect.sendKeys(deviceType);
    }

    async getDeviceTypeSelect() {
        return this.deviceTypeSelect.element(by.css('option:checked')).getText();
    }

    async deviceTypeSelectLastOption() {
        await this.deviceTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setDataSheetInput(dataSheet) {
        await this.dataSheetInput.sendKeys(dataSheet);
    }

    async getDataSheetInput() {
        return this.dataSheetInput.getAttribute('value');
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

export class SystemDeviceTypeDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-systemDeviceType-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-systemDeviceType'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}