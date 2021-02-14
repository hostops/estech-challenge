/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SystemDeviceDataComponentsPage, SystemDeviceDataDeleteDialog, SystemDeviceDataUpdatePage } from './system-device-data.page-object';

const expect = chai.expect;

describe('SystemDeviceData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let systemDeviceDataUpdatePage: SystemDeviceDataUpdatePage;
    let systemDeviceDataComponentsPage: SystemDeviceDataComponentsPage;
    let systemDeviceDataDeleteDialog: SystemDeviceDataDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SystemDeviceData', async () => {
        await navBarPage.goToEntity('system-device-data');
        systemDeviceDataComponentsPage = new SystemDeviceDataComponentsPage();
        await browser.wait(ec.visibilityOf(systemDeviceDataComponentsPage.title), 5000);
        expect(await systemDeviceDataComponentsPage.getTitle()).to.eq('wpcemApp.systemDeviceData.home.title');
    });

    it('should load create SystemDeviceData page', async () => {
        await systemDeviceDataComponentsPage.clickOnCreateButton();
        systemDeviceDataUpdatePage = new SystemDeviceDataUpdatePage();
        expect(await systemDeviceDataUpdatePage.getPageTitle()).to.eq('wpcemApp.systemDeviceData.home.createOrEditLabel');
        await systemDeviceDataUpdatePage.cancel();
    });

    it('should create and save SystemDeviceData', async () => {
        const nbButtonsBeforeCreate = await systemDeviceDataComponentsPage.countDeleteButtons();

        await systemDeviceDataComponentsPage.clickOnCreateButton();
        await promise.all([
            systemDeviceDataUpdatePage.setTimestampInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            systemDeviceDataUpdatePage.setDataValueInput('5'),
            systemDeviceDataUpdatePage.dataTypeSelectLastOption(),
            systemDeviceDataUpdatePage.setNameInput('name'),
            systemDeviceDataUpdatePage.setUnitInput('unit'),
            systemDeviceDataUpdatePage.deviceSelectLastOption()
        ]);
        expect(await systemDeviceDataUpdatePage.getTimestampInput()).to.contain('2001-01-01T02:30');
        expect(await systemDeviceDataUpdatePage.getDataValueInput()).to.eq('5');
        expect(await systemDeviceDataUpdatePage.getNameInput()).to.eq('name');
        expect(await systemDeviceDataUpdatePage.getUnitInput()).to.eq('unit');
        await systemDeviceDataUpdatePage.save();
        expect(await systemDeviceDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await systemDeviceDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last SystemDeviceData', async () => {
        const nbButtonsBeforeDelete = await systemDeviceDataComponentsPage.countDeleteButtons();
        await systemDeviceDataComponentsPage.clickOnLastDeleteButton();

        systemDeviceDataDeleteDialog = new SystemDeviceDataDeleteDialog();
        expect(await systemDeviceDataDeleteDialog.getDialogTitle()).to.eq('wpcemApp.systemDeviceData.delete.question');
        await systemDeviceDataDeleteDialog.clickOnConfirmButton();

        expect(await systemDeviceDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
