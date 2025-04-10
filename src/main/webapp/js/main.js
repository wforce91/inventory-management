const form = document.getElementById('productForm');
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

async function loadProducts() {
  const res = await fetch('/inventory-management/api/products');
  const products = await res.json();

  list.innerHTML = '';
  products.forEach(p => {
    const item = document.createElement('li');
    item.textContent = `${p.name} - ${p.quantity}`;
    list.appendChild(item);
  });
}

loadProducts();
