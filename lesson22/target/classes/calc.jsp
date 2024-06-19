<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
    <h1>Calculator</h1>
    <form action="/jee-web/calculator/add" method="post">
        <label for="a"></label>
        <input type="text" id="a" name="a">
        <label for="b"></label>
        <input type="text" id="b" name="b">
        <button type="submit">Add</button>
    </form>
    <form action="/jee-web/calculator/subtract" method="post">
        <label for="a"></label>
        <input type="text" id="a" name="a">
        <label for="b"></label>
        <input type="text" id="b" name="b">
        <button type="submit">Subtract</button>
    </form>
    <form action="/jee-web/calculator/multiply" method="post">
        <label for="a"></label>
        <input type="text" id="a" name="a">
        <label for="b"></label>
        <input type="text" id="b" name="b">
        <button type="submit">Multiply</button>
    </form>
    <form action="/jee-web/calculator/divide" method="post">
        <label for="a"></label>
        <input type="text" id="a" name="a">
        <label for="b"></label>
        <input type="text" id="b" name="b">
        <button type="submit">Divide</button>
    </form>
</body>
</html>