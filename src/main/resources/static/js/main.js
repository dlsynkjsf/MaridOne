//calls all dashboard uses

function logout() {

}

function download(material) {

    //control access here using api calls
}

function viewMaterials(type) {
    //control access here using api calls
}

async function editInfo(empId) {
    const response = await fetch(`/api/employees/self/${empId}`)
    const result = await response.json();
    console.log(result);

}

async function showNotifications(type) {
    let response;
    if (type === 'all') {
        response = await fetch("/api/employees/notifications/all");
    }
    else {
        response = await fetch("/api/employees/notifications");
    }
    const result = await response.json();
    //PROCESS
    console.log(result);
}