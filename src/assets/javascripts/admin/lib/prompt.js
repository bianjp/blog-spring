function flashPrompt(message){
  var existingNode = document.querySelector('.flash-prompt');
  if (existingNode) {
    document.body.removeChild(existingNode);
  }

  var node = document.createElement('div');
  node.className = 'flash-prompt';
  node.innerHTML = "<span>" + message + "</span>";

  var listener = function(){
    this.remove();
  };

  if ('onanimationend' in node){
    node.addEventListener('animationend', listener);
  }
  else {
    node.addEventListener('webkitAnimationEnd', listener);
    node.addEventListener('MSAnimationEnd', listener);
    node.addEventListener('oAnimationEnd', listener);
  }

  document.body.appendChild(node);
}
