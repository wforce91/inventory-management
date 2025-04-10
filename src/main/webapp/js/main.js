const form = document.getElementById('productForm');
const updateBtn = document.getElementById('updateBtn');
const list = document.getElementById('productList');

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const name = document.getElementById('name').value;
  const quantity = document.getElementById('quantity').value;

  await fetch('/inventory-management/api/products', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ name, quantity })
  });

  form.reset();
  loadProducts();
});

updateBtn.addEventListener('click', async () => {
  const id = document.getElementById('productId').value;
  const name = document.getElementById('name').value;
  const quantity = document.getElementById('quantity').value;

  if (!id) {
    alert("Please enter a product ID to update.");
    return;
  }

  const response = await fetch(`/inventory-management/api/products/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ name, quantity })
  });

  if (response.ok) {
    alert("Product updated successfully");
    form.reset();
    loadProducts();
  } else {
    alert("Update failed: Product not found.");
  }
});

async function loadProducts() {
  const res = await fetch('/inventory-management/api/products');
  const products = await res.json();

  list.innerHTML = '';
  products.forEach(p => {
    const item = document.createElement('li');
    item.textContent = `ID: ${p.id} | ${p.name} - ${p.quantity}`;
    list.appendChild(item);
  });
}

loadProducts();
